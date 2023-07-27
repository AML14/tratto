from flask import Flask, jsonify, request, make_response
from scripts.server import predictor_decoder


class Server:
    def __init__(
            self,
            device,
            classification_type_token_classes,
            classification_type_token_values,
            transformer_type_token_classes,
            transformer_type_token_values,
            model_token_classes,
            model_token_values,
            tokenizer_token_classes,
            tokenizer_token_values
    ):
        self._app = Flask(__name__)
        self._device = device
        self._classification_type_token_classes = classification_type_token_classes
        self._classification_type_token_values = classification_type_token_values
        self._transformer_type_token_classes = transformer_type_token_classes
        self._transformer_type_token_values = transformer_type_token_values
        self._model_token_classes = model_token_classes
        self._model_token_values = model_token_values
        self._tokenizer_token_classes = tokenizer_token_classes
        self._tokenizer_token_values = tokenizer_token_values
        self.setup_routes()

    def next_token(self):
        filename = request.args.get('filename')
        if filename:
            token = predictor_decoder.next_token(
                self._device,
                filename,
                self._classification_type_token_classes,
                self._classification_type_token_values,
                self._transformer_type_token_classes,
                self._transformer_type_token_values,
                self._model_token_classes,
                self._model_token_values,
                self._tokenizer_token_classes,
                self._tokenizer_token_values
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

