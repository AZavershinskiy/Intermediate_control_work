import controller


def save_note(id, date, note):
    with open(controller.path, 'a') as file:
        file.write(f'{id},{date},{note};\n')


def read_note():
    with open(controller.path, 'r') as file:
        return file.readline()[controller.note_id]


def read_all():
    with open(controller.path, 'r') as file:
        return file.readlines()
