from transformers import RobertaConfig, RobertaTokenizer, RobertaForSequenceClassification

class ModelClasses:

    @staticmethod
    def get_available_model_classes():
        return ['roberta']

    @staticmethod
    def getModelClass(model_name: str) :
        if model_name == 'roberta':
            return RobertaConfig, RobertaForSequenceClassification, RobertaTokenizer