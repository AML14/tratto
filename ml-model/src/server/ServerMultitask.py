from flask import Flask, jsonify, request, make_response
from scripts.server import predictor_multitask
from src.exceptions.TokenNotFoundException import TokenNotFoundException


class ServerMultitask:
    def __init__(
            self,
            device,
            transformer_type,
            model,
            tokenizer,
            max_src_length,
            max_tgt_length
    ):
        self._app = Flask(__name__)
        self._device = device
        self._transformer_type = transformer_type
        self._model = model
        self._tokenizer = tokenizer
        self._max_src_length = max_src_length
        self._max_tgt_length = max_tgt_length
        self.setup_routes()

    def next_token(self):
        filename = request.args.get('filename')
        if filename:
            try:
                token = predictor_multitask.predict_next_token(
                    self._device,
                    filename,
                    self._transformer_type,
                    self._model,
                    self._tokenizer,
                    self._max_src_length,
                    self._max_tgt_length
                )
                # Create a response object with the string content
                response = make_response(token)
                # Optionally, set any additional headers if needed
                response.headers['Content-Type'] = 'text/plain'
                return response
            except TokenNotFoundException as e:
                return str(e.predicted_token), 404
        else:
            return 'Missing filename parameter', 400  # Return an error if the 'data' parameter is missing
        return jsonify({'token': token})

    def setup_routes(self):
        self._app.add_url_rule('/api/next_token', 'next_token', self.next_token, methods=['GET'])

    def run(self, port):
        self._app.run(port=port)
