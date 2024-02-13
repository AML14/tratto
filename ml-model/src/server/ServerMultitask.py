from flask import Flask, jsonify, request, make_response
from scripts.server import predictor_multitask


class ServerMultitask:
    def __init__(
            self,
            device,
            classification_type_oracles,
            classification_type_token_classes,
            classification_type_token_values,
            transformer_type,
            model,
            tokenizer
    ):
        self._app = Flask(__name__)
        self._device = device
        self._classification_type_oracles = classification_type_oracles
        self._classification_type_token_classes = classification_type_token_classes
        self._classification_type_token_values = classification_type_token_values
        self._transformer_type = transformer_type
        self._model = model
        self._tokenizer = tokenizer
        self.setup_routes()

    def next_token(self):
        filename = request.args.get('filename')
        if filename:
            token = predictor_multitask.next_token(
                self._device,
                filename,
                self._classification_type_oracles,
                self._classification_type_token_classes,
                self._classification_type_token_values,
                self._transformer_type,
                self._model,
                self._tokenizer
            )
            # Create a response object with the string content
            response = make_response(token)
            # Optionally, set any additional headers if needed
            response.headers['Content-Type'] = 'text/plain'
            return response
        else:
            return 'Missing filename parameter', 400  # Return an error if the 'data' parameter is missing
        return jsonify({'token': token})

    def setup_routes(self):
        self._app.add_url_rule('/api/next_token', 'next_token', self.next_token, methods=['GET'])

    def run(self, port):
        self._app.run(port=port)

