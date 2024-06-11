import os
import json
import openai
from openai import OpenAI
from dotenv import load_dotenv, find_dotenv
_ = load_dotenv(find_dotenv())

openai.api_key=os.getenv("OPENAI_API_KEY")
client = OpenAI()

openai_dir = os.path.dirname(__file__)

uploaded_files = list(map(lambda f: { "id": f.id, "filename": f.filename }, client.files.list().data))

for file in uploaded_files:
    print(f"Deleting file: {file['id']}")
    client.files.delete(file["id"])