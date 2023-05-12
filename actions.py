from datetime import datetime


def create_note(notes):
    title = input('Input Title: ')
    text = input('Input Text: ')
    note = [new_id(notes), get_date(), title, text]
    return note


def find_note(notes):
    note_id = input('Input Note ID: ')
    for note in notes:
        if note_id in note:
            return note


def show_all(notes):
    return notes


def show_by_date(notes):
    selected_date = input(
        'Input the date of the Notes in the format: dd/mm/yyyy: ')
    filtered_notes = []
    for note in notes:
        if selected_date in note[1]:
            filtered_notes.append(note)
    return filtered_notes


def edit_note(notes):
    note = find_note(notes)
    new_title = input('Input Title: ')
    new_note = input('Input Text: ')
    edited_note = [note[0], note[1], new_title, new_note]
    updated_notes = []
    
    return updated_notes


def delete_note(notes):
    return


def new_id(notes):
    if (notes == []):
        return '1'
    else:
        return str(int(notes[-1][0])+1)


def get_date():
    now = datetime.now()
    now_f = now.strftime("%d/%m/%Y-%H:%M:%S")
    return now_f
