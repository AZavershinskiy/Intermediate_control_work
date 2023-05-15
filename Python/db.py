import csv
import os


def read_file(path):
    notes = []
    if (os.path.exists(path)):
        with open(path, 'r') as f:
            reader = csv.reader(f, delimiter=';')
            next(reader)
            for note in reader:
                notes.append(list(note))
        return notes
    else:
        with open(path, 'w') as f2:
            writer = csv.writer(f2, delimiter=';')
            writer.writerow(headers)
        return notes


def save_to_file(new_note):
    with open(path, 'a') as f:
        writer = csv.writer(f, delimiter=';')
        writer.writerow(new_note)
    read_file(path)


def update_file(updated_notes):
    with open(path, 'w') as f:
        writer = csv.writer(f, delimiter=';')
        writer.writerow(headers)
        for note in updated_notes:
            writer.writerow(note)
    read_file(path)


path = 'Python/Notes.csv'

headers = ['ID',
           'DATE',
           'TITLE',
           'NOTE']
