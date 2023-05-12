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


def output_note(note):
    try:
        print(
            f'\nID: {note[0]}\nDate: {note[1]}\nHeader: {note[2]}\nNote: {note[3]}')
    except:
        print('\nID NOT FOUND')


def output_all(notes):
    if (notes != []):
        print(f'\n\nFind {len(notes)} Notes:')
        for note in notes:
            print(
                f'\nID: {note[0]}\nDate: {note[1]}\nHeader: {note[2]}\nNote: {note[3]}')
    else:
        print('\nERROR! NOTES ARE EMPTY',
              '\nCREATE YOUR FIRST NOTE')


def output_by_date(notes):
    if (notes != []):
        print(f'\n\nFind {len(notes)} Notes:')
        for note in notes:
            print(
                f'\nID: {note[0]}\nDate: {note[1]}\nHeader: {note[2]}\nNote: {note[3]}')
    else:
        print('\nERROR! NOTES NOT FOUND',
              '\nCHECK DATE INPUT')
