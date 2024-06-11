import os
import json
import openai
from openai import OpenAI
from dotenv import load_dotenv, find_dotenv
_ = load_dotenv(find_dotenv())

openai.api_key=os.getenv("OPENAI_API_KEY")
client = OpenAI()

batches_dir = os.path.join(os.path.dirname(__file__), "batches")

for file in os.listdir(batches_dir):
    batch_input_file = client.files.create(
      file=open(os.path.join(batches_dir, file), "rb"),
      purpose="batch"
    )