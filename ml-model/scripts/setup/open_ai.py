from dotenv import load_dotenv
import os
import sys
import openai
import tiktoken

sys.path.append(os.path.join(os.path.dirname(os.path.abspath(__file__)),"..", ".."))

from src.utils import utils

def num_tokens_from_string(string: str, encoding_name: str) -> int:
    """Returns the number of tokens in a text string."""
    encoding = tiktoken.encoding_for_model("gpt-3.5-turbo")
    num_tokens = len(encoding.encode(string))
    return num_tokens

load_dotenv()

OPEN_AI_API_KEY = os.getenv('OPEN_AI_API_KEY')
openai.api_key = OPEN_AI_API_KEY

_, preface = utils.import_json(os.path.join(os.path.dirname(os.path.abspath(__file__)),"..", "..", "src", "resources", "openai_preface_assistant.json"))
_,original_javadoctags_set = utils.import_json(os.path.join(os.path.dirname(os.path.abspath(__file__)),"..", "..", "src", "resources", "original_javadoctags_set.json"))

output_path = os.path.join(os.path.dirname(os.path.abspath(__file__)),"..", "..", "src", "resources", "open_ai_results")
encoding = tiktoken.encoding_for_model("gpt-3.5-turbo")


for idx, original_javadoctags in enumerate(original_javadoctags_set):
    if idx > 9:
        messages = [
            {
                "role": "user",
                "content": preface["user"]
            },
            {
                "role": "assistant",
                "content": preface["assistant"]
            },
            {
                "role": "user",
                "content": f"input={original_javadoctags}"
            }
        ]
        num_tokens = 0
        for msg in messages:
            num_tokens += num_tokens_from_string(msg["content"], "gpt-3.5-turbo")
        print(f"Processing batch {idx}/{len(original_javadoctags_set)} - Tokens: {num_tokens}")
        try:
            response = openai.ChatCompletion.create(
                model = "gpt-3.5-turbo",
                messages = messages
            )
            reply = response["choices"][0]["message"]["content"]

            if not os.path.exists(output_path):
                os.makedirs(output_path)
            utils.export_stats(os.path.join(output_path, f"output_{idx}.json"), reply)
        except:
            print(f"Failed to process batch {idx}/{len(original_javadoctags_set)} - Tokens: {num_tokens} (502 Bad Gataway error)")
