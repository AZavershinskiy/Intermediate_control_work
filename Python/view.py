def welcom():
    print('\nWelcome to NOTES')


def goodbye():
    print('\nGOODBYE!')


def menu():
    print("""
    MENU:
    1 - Create note
    2 - Show note
    3 - Show all notes
    4 - Show by date
    5 - Edit note
    6 - Delete note
    0 - EXIT
    """)


def input_command():
    return input('Input command: ')


def input_id():
    return input('Input Note ID: ')


def selected_date():
    return input('Input the date of the Notes: dd/mm/yyyy: ')


def input_title():
    return input('Input Note title: ')


def input_note_text():
    return input('Input Note text: ')


def output_note(note):
    try:
        print('\n -----------------------',
              f'\nID: {note[0]}\nDATE: {note[1]}\nTITLE: {note[2]}\nNOTE: {note[3]}\n',
              '-----------------------')
    except:
        id_not_found()


def output_all(notes):
    if (notes != []):
        if (len(notes) > 1):
            print(f'\nFound {len(notes)} Notes:')
        else:
            print(f'\nFind {len(notes)} Note:')
        for note in notes:
            print(' -----------------------',
                  f'\nID: {note[0]}\nDATE: {note[1]}\nTITLE: {note[2]}\nNOTE: {note[3]}\n',
                  '-----------------------')
    else:
        print('\nERROR! NOTES ARE EMPTY',
              '\nCREATE YOUR FIRST NOTE')


def output_by_date(notes):
    if (notes != []):
        if (len(notes) > 1):
            print(f'\nFound {len(notes)} Notes:')
        else:
            print(f'\nFind {len(notes)} Note:')
        for note in notes:
            print(' -----------------------',
                  f'\nID: {note[0]}\nDATE: {note[1]}\nTITLE: {note[2]}\nNOTE: {note[3]}\n',
                  '-----------------------')
    else:
        print('\nERROR! NOTES NOT FOUND',
              '\nOR CHECK YOUR DATE INPUT')


def created_ok():
    print('\nNOTE CREATED SUCCESSFULLY')


def changed_ok():
    print('\nNOTE CHANGED SUCCESSFULLY')


def deleted_ok():
    print('\nNOTE DELETED SUCCESSFULLY')


def input_error():
    print('\nINPUT ERROR! PLEASE TRY AGAIN')


def id_not_found():
    print('\nERROR! NOTE NOT FOUND',
          '\nOR CHECK YOUR ID INPUT')
