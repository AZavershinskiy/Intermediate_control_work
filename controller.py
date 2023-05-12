import actions
import db
import view


def commands():
    print('\nWelcome to NOTES')
    while (True):
        view.menu()
        match input('Command: '):
            case '0':  # Exit
                print('Good bye!')
                return
            case '1':  # Create note
                db.save_to_file(actions.create_note(db.read_file(db.path)))
            case '2':  # Show note
                view.output_note(actions.find_note(db.read_file(db.path)))
            case '3':  # Show all notes
                view.output_all(db.read_file(db.path))
            case '4':  # Show by date
                view.output_by_date(actions.show_by_date(db.read_file(db.path)))
            case '5':  # Edit note
                db.update_file(db.path)
            case '6':  # Delete note
                actions.delete_note(db.path)
            case _:  # Error
                print('Input Error! Please try again')


