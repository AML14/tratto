import re
import csv
import sys


if __name__ == "__main__":
    # Define local variables:
    #   * fqn         : save the current class under analysis
    #   * oracle_time : saves the time to complete the generation of a single oracle
    #   * empty       : is a boolean value that indicates if the oracle generated is empty (`;`)
    #   * m_o         : is a boolean value that indicates if match-checking for oracle completion has been performed
    #   * m_e         : is a boolean to check if an empty oracles match-checking for empty oracle generated has been performed
    file_path = sys.argv[1]
    fqn = ""
    oracle_time = 0
    empty = False
    m_o = False
    m_e = False
    pattern_class = re.compile(r'Generation of EvoSuite tests skipped for class (\S+) \(supposed has already been generated\).')
    pattern_time = re.compile(r'Oracle completed in: (\d+) milliseconds')
    pattern_empty_oracle = re.compile(r'Tratto:110 - Final oracle:([^;]+)')
    empty_counter = 0
    oracle_counter = 0
    class_counter = 0
    numbers = []

    with open(file_path, 'r') as file:
        for line in file:
            # Step 2: Check each line for the pattern
            match_class = pattern_class.search(line)
            match_time = pattern_time.search(line)
            match_empty_oracle = pattern_empty_oracle.search(line)
            if match_class:
                fqn = match_class.group(1)
                class_counter = class_counter + 1
            if match_empty_oracle:
                if m_e == True:
                    raise ValueError("Unexpected state")
                m_e = True
                empty = True if not match_empty_oracle.group(1).strip() else False
                empty_counter = empty_counter + 1
            if match_time:
                if m_o == True:
                    raise ValueError("Unexpected state")
                m_o = True
                oracle_time = match_time.group(1)
                oracle_counter = oracle_counter + 1
            if m_o and m_e:
                # Step 3: Extract the number if the pattern matches
                numbers.append((fqn, oracle_time, empty))
                m_o = False
                m_e = False

    # Step 4: Save the extracted numbers to a CSV file
    with open('output_time.csv', 'w', newline='') as csvfile:
        csvwriter = csv.writer(csvfile)
        for fqn_row, time_row, empty_row in numbers:
            csvwriter.writerow([fqn_row, time_row, empty_row])

    print(f"Classes counter: {class_counter}")
    print(f"Oracles counter: {oracle_counter}")
    print(f"Empties counter: {empty_counter}")