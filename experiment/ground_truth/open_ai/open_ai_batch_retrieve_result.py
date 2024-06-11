import os
import json
import openai
from openai import OpenAI
from dotenv import load_dotenv, find_dotenv
_ = load_dotenv(find_dotenv())

openai.api_key=os.getenv("OPENAI_API_KEY")
client = OpenAI()

stats = {}

output_dir = os.path.join(os.path.dirname(__file__), "output_batches")

if not os.path.exists(output_dir):
    os.makedirs(output_dir)

with open(os.path.join(os.path.dirname(__file__), "stats.json"), 'r') as stats_file:
    stats = json.load(stats_file)

for batch_stats in stats["completed"]:
    print(f"Retrieving result of batch: {batch_stats['id']}")
    batch_file = client.batches.retrieve(batch_stats['id'])
    input_file = client.files.retrieve(batch_stats['input_file_id'])
    output_content = client.files.content(batch_file.output_file_id).content.decode('utf-8')
    with open(os.path.join(output_dir, f"output_{input_file.filename}"), 'w') as stats_file:
        stats_file.write(output_content)