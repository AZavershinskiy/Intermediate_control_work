from datetime import datetime


def create_note(notes: list, title: str, note_text: str):
    note = [new_id(notes), get_date(), title, note_text]
    return note


def find_note(notes: list, note_id: str):
    for note in notes:
        if note_id in note:
            return note


def show_all(notes: list):
    return notes


def show_by_date(notes: list, selected_date: str):
    filtered_notes = []
    for note in notes:
        if selected_date in note[1]:
            filtered_notes.append(note)
    return filtered_notes


def edit_note(notes: list, note_id: str, new_title: str, new_note: str):
    note = find_note(notes, note_id)
    edited_note = [note[0], get_date(), new_title, new_note]
    notes.remove(note)
    notes.append(edited_note)
    notes.sort()
    return notes


def delete_note(notes: list, note_id: str):
    note = find_note(notes, note_id)
    notes.remove(note)
    return notes


def new_id(notes: list):
    if (notes == []):
        return '1'
    else:
        return str(int(notes[-1][0])+1)


def get_date():
    now = datetime.now()
    now_f = now.strftime("%d/%m/%Y-%H:%M:%S")
    return now_f
