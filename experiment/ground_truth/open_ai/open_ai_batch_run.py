import os
import json
import openai
import time
from openai import OpenAI
from dotenv import load_dotenv, find_dotenv
_ = load_dotenv(find_dotenv())

openai.api_key=os.getenv("OPENAI_API_KEY")
client = OpenAI()

openai_dir = os.path.dirname(__file__)
files_ids = []
stats = {}

with open(os.path.join(os.path.dirname(__file__), "open_ai_files_ids.json"), 'r') as file:
    files_ids = json.load(file)



for file_data in files_ids:
    print(f"Processing batch: {file_data['id']}")
    start_time = time.time()
    batch = client.batches.create(
        input_file_id=file_data['id'],
        endpoint="/v1/chat/completions",
        completion_window="24h",
        metadata={
          "description": "nightly eval job"
        }
    )
    print(f"Created batch: {batch.id}")

    while(batch.status in ['validating', 'in_progress', 'finalizing']):
        print(f"Status: {batch.status}")
        time.sleep(3)
        batch = client.batches.retrieve(batch.id)

    end_time = time.time()
    elapsed_time = end_time - start_time

    print(f"Batch terminated in {elapsed_time:.2f} with status: {batch.status}")
    print(f"Errors: {batch.errors}")

    if batch.status not in stats:
        stats[batch.status] = []

    stats[batch.status].append({
        "id": batch.id,
        "input_file_id": batch.input_file_id,
        "errors": batch.errors,
        "request_counts": {
            "total": batch.request_counts.total,
            "completed": batch.request_counts.completed,
            "failed": batch.request_counts.failed
        },
        "elapsed_time": f"{elapsed_time:.2f}"
    })

    with open(os.path.join(openai_dir, "stats.json"), 'w') as stats_file:
        json.dump(stats, stats_file, indent=4)