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
    print(f"Processing batch: {batch_data.id}")
    batch_status = client.batches.retrieve(batch_data.id)
    print(batch_status)