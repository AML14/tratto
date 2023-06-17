from flask import Flask, jsonify, request

import predictor


class Server:
    def __int__(
            self,
            device,
            model_token_classes,
            model_token_values,
            tokenizer_token_classes,
            tokenizer_token_values
    ):
        self._app = Flask(__name__)
        self._model_token_classes = model_token_classes
        self._model_token_values = model_token_values
        self._tokenizer_token_classes = tokenizer_token_classes
        self._tokenizer_token_values = tokenizer_token_values

    def next_token(self):
        filename = request.args.get('filename')
        if filename:
            token = predictor.nextToken(
                self.device,
                filename,
                self._model_token_classes,
                self._model_token_values
            )
            return token
        else:
            return 'Missing filename parameter', 400  # Return an error if the 'data' parameter is missing
        return jsonify({'token': token})

    def run(self):
        self._app.add_url_rule("/api/next_token", self.next_token)
        self._app.run()

