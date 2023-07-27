from transformers import RobertaConfig, RobertaTokenizer, RobertaForSequenceClassification, T5ForConditionalGeneration, T5Config
from transformers import AutoConfig, AutoTokenizer, AutoModelForSequenceClassification

from src.types.TransformerType import TransformerType


class ModelClasses:

    @staticmethod
    def get_available_model_classes():
        return ['roberta']

    @staticmethod
    def getModelClass(model_name: str) :
        if model_name == 'roberta':
            return RobertaConfig, RobertaForSequenceClassification, RobertaTokenizer, TransformerType.ENCODER
        if model_name == 'codet5':
            return T5Config, T5ForConditionalGeneration, RobertaTokenizer, TransformerType.DECODER
        if model_name == 'codet5+':
            return T5Config, T5ForConditionalGeneration, AutoTokenizer, TransformerType.DECODER