from flask import Flask, jsonify, request, make_response
from queue import Queue
import threading
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
        self._request_queue = Queue()
        self.setup_routes()

    def next_token(self):
        filename = request.args.get('filename')
        if filename:
            # Enqueue the request for processing
            self._request_queue.put({
                'filename': filename
            })
            return 'Request queued'
        else:
            return 'Missing filename parameter', 400  # Return an error if the 'data' parameter is missing
        return jsonify({'token': token})

    def process_request(self):
        while True:
            # Dequeue a request
            task = self._request_queue.get()
            # Process the request
            filename = task['filename']
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
                print("Processed request for:", filename)
            except TokenNotFoundException as e:
                print("Error processing request for:", filename)
                response = make_response(str(e.predicted_token), 404)
                response.headers['Content-Type'] = 'text/plain'
            # Send the response
            response.send()
            # Mark the task as done
            self._request_queue.task_done()

    def setup_routes(self):
        self._app.add_url_rule('/api/next_token', 'next_token', self.next_token, methods=['GET'])

    def run(self, port):
        # Start the request processing thread
        worker_thread = threading.Thread(target=self.process_request)
        worker_thread.daemon = True
        worker_thread.start()
        self._app.run(port=port)
