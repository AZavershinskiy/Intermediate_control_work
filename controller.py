import actions
from view import request


def commands():
    while (request() != '0'):
        match request():
            case '1':
                return actions.create_note(path, last_id)
            case '2':
                return actions.read_note(path, note_id)
            case '3':
                return actions.read_all(path)
            case '4':
                return actions.edit_note(path, note_id)
            case '5':
                return actions.delete_note(path, note_id)
            case _:
                return 'Input Error'


path = 'Notes.cvc'

note_id = int(request())
last_id = actions.last_id(path)
