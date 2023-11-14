import math
import pandas as pd
import numpy as np
import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

cred = credentials.Certificate("m2t-tratto.json")
firebase_admin.initialize_app(cred)

firestore = firebase_admin.firestore.client()

df = pd.read_csv('db.csv')
df = df.replace(np.nan, '', regex=True)

row_counter = 1
batch_size = 500
total_rows = df.shape[0]
total_batches = math.floor(total_rows/batch_size)


# Split the data into batches of 500
batches = []
for batch_idx, i in enumerate(range(0, 10000, batch_size)):
    #print(f"Processing batch {batch_idx + 1} of {total_batches}")

    batch = firestore.batch()
    # Iterate over the rows in the batch
    for idx, row in df[i:i + 500].iterrows():
        # Iterate through the elements in each row
        if len(row) == 4:  # Ensure that there are 4 columns in the row
            _, test, fm, docstring = row
            doc_ref = firestore.collection("tratto_m2t").document(f"{idx}")
            print(f"Processed row {idx + 1} of {total_rows}")
            # Add the document to the batch
            batch.set(doc_ref, {
                'id': idx,
                'test': test,
                'fm': fm,
                'docstring': docstring,
                'oracles': [],
                'oracles_counter': 0
            })
        else:
            print(f"Skipping row with {len(row)} columns: {row}")
    # Add the batch to the list of batches
    batches.append(batch)

# Commit the batches
for batch in batches:
    batch.commit()