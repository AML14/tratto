import os
import sys
import json
import copy

if __name__ == '__main__':
    # Path to classes methods
    classes_methods_path = os.path.join(os.path.dirname(__file__), 'methods')
    # Path to default query
    query_file_path = os.path.join(os.path.dirname(__file__), 'query.txt')
    # Path to output file
    open_ai_query_batch_json_file_path = os.path.join(os.path.dirname(__file__), 'open_ai_batch.json')
    open_ai_query_batch_jsonl_file_path = os.path.join(os.path.dirname(__file__), 'open_ai_batch.jsonl')
    query_content = ""
    # Open the file and read its content
    with open(query_file_path, 'r') as query_file:
        query_content = query_file.read()

    request_counter = 0
    open_ai_requests = []

    for class_methods_file_path in os.listdir(classes_methods_path):
        request_layout = {
            "custom_id":"request-",
            "method":"POST",
            "url":"/v1/chat/completions",
            "body":{
               "model":"gpt-4o",
               "messages":[
                  {
                     "role":"system",
                     "content":"You test software and you have to generate oracles for Java methods."
                  },
                  {
                     "role":"user",
                     "content": ""
                  }
               ],
               "max_tokens": 2000
            }
        }

        class_methods = []

        with open(os.path.join(classes_methods_path, class_methods_file_path), 'r') as class_method_file:
            class_methods = json.load(class_method_file)

        for class_method in class_methods:
            method_query = query_content.replace("<OPEN_AI_QUERY_METHOD>", class_method["methodSourceCode"])
            request = copy.deepcopy(request_layout)
            request["body"]["messages"][1]["content"] = method_query
            request["custom_id"] = request["custom_id"] + f"{request_counter}"
            class_method["custom_id"] = request["custom_id"]
            open_ai_requests.append(request)
            request_counter = request_counter + 1

        with open(os.path.join(classes_methods_path, class_methods_file_path), 'w') as class_method_file:
            json.dump(class_methods, class_method_file, indent=4)

    with open(open_ai_query_batch_json_file_path, 'w') as open_ai_batch_file:
        json.dump(open_ai_requests, open_ai_batch_file, indent=4)

    if os.path.exists(open_ai_query_batch_jsonl_file_path):
        os.remove(open_ai_query_batch_jsonl_file_path)

    with open(open_ai_query_batch_jsonl_file_path, 'a') as open_ai_batch_file:
        for request in open_ai_requests:
            open_ai_batch_file.write(json.dumps(request) + '\n')


