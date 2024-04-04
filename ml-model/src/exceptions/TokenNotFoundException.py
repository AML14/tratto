class TokenNotFoundException(Exception):
    def __init__(self, message, predicted_token=None):
        super().__init__(message)
        self.predicted_token = predicted_token