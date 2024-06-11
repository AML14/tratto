import os
import json
import openai
from openai import OpenAI
from dotenv import load_dotenv, find_dotenv
_ = load_dotenv(find_dotenv())

openai.api_key=os.getenv("OPENAI_API_KEY")
client = OpenAI()

batches_ids = client.batches.list()

for batch_data in batches_ids:
    if not batch_data.status == "failed" and not batch_data.status == "completed":
        print(f"Deleting batch: {batch_data.id}")
        client.batches.cancel(batch_data.id)