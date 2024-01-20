from dotenv import load_dotenv
import os
import sys
import openai
import tiktoken

sys.path.append(os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", ".."))

from src.utils import utils

load_dotenv()

OPEN_AI_API_KEY = os.getenv('OPEN_AI_API_KEY')
print(f"OPEN_AI_API_KEY: {OPEN_AI_API_KEY}")
openai.api_key = OPEN_AI_API_KEY

_, preface = utils.import_json(os.path.join(os.path.dirname(os.path.abspath(__file__)), "resources", "openai_preface_assistant.json"))
_, original_javadoctags_set = utils.import_json(os.path.join(os.path.dirname(os.path.abspath(__file__)), "resources", "original_javadoctags_set.json"))

output_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "resources", "open_ai_results")
encoding = tiktoken.encoding_for_model("gpt-3.5-turbo")


def num_tokens_from_string(string: str, encoding_name: str) -> int:
    """Returns the number of tokens in a text string."""
    encoding = tiktoken.encoding_for_model("gpt-3.5-turbo")
    num_tokens = len(encoding.encode(string))
    return num_tokens

if __name__ == '__main__':
    # Iterate over the sets of original javadoc tags
    for idx, original_javadoctags in enumerate(original_javadoctags_set):
        # Generate list of messages as input to OpenAI request
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

        # Compute total number of tokens as input to the OpenAI request (to monitor costs)
        num_tokens = 0
        for msg in messages:
            num_tokens += num_tokens_from_string(msg["content"], "gpt-3.5-turbo-instruct")
        print(f"Processing batch {idx}/{len(original_javadoctags_set)} - Tokens: {num_tokens}")
        try:
            # Perform request to OpenAI and save response
            response = openai.ChatCompletion.create(
                model = "gpt-3.5-turbo",
                messages = messages
            )
            # Read content of the response
            content = response["choices"][0]["message"]["content"]
            # Save response
            if not os.path.exists(output_path):
                os.makedirs(output_path)
            utils.export_stats(os.path.join(output_path, f"output_{idx}.json"), content)
        except:
            print(f"Failed to process batch {idx}/{len(original_javadoctags_set)} - Tokens: {num_tokens} (502 Bad Gataway error)")
