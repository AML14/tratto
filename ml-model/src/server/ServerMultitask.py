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
            task_event = threading.Event()
            self._request_queue.put({
                'filename': filename,
                'event': task_event
            })
            # Wait for task completion
            task_event.wait()
            task_result = task_event.result

            if 'error' in task_result:
                # Create a response object with the string content
                response = make_response(task_result['error'], 404)
                # Optionally, set any additional headers if needed
                response.headers['Content-Type'] = 'text/plain'
                return response
            else:
                token = task_result['token']
                # Create a response object with the string content
                response = make_response(token)
                # Optionally, set any additional headers if needed
                response.headers['Content-Type'] = 'text/plain'
                return response
        else:
            return 'Missing filename parameter', 400

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
                task['token'] = token
                print("Processed request for:", filename)
            except TokenNotFoundException as e:
                print("Error processing request for:", filename)
                task['error'] = str(e.predicted_token)
            # Notify the waiting thread with the result
            task['event'].result = task
            task['event'].set()
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
