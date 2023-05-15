import actions
import db
import view


def commands():
    view.welcom()
    while (True):
        view.menu()
        match view.input_command():
            case '0':  # Exit
                view.goodbye()
                return
            case '1':  # Create note
                note1 = actions.create_note(db.read_file(
                    db.path), view.input_title(), view.input_note_text())
                db.save_to_file(note1)
                view.created_ok()
                view.output_note(actions.find_note(
                    db.read_file(db.path), note1[0]))
            case '2':  # Show note
                view.output_note(actions.find_note(
                    db.read_file(db.path), view.input_id()))
            case '3':  # Show all notes
                view.output_all(actions.show_all(db.read_file(db.path)))
            case '4':  # Show by date
                view.output_by_date(
                    actions.show_by_date(db.read_file(db.path), view.selected_date()))
            case '5':  # Edit note
                try:
                    note5 = actions.find_note(
                        db.read_file(db.path), view.input_id())
                    db.update_file(actions.edit_note(db.read_file(
                        db.path), note5[0], view.input_title(), view.input_note_text()))
                    view.changed_ok()
                    view.output_note(actions.find_note(
                        db.read_file(db.path), note5[0]))
                except:
                    view.id_not_found()
            case '6':  # Delete note
                try:
                    db.update_file(actions.delete_note(
                        db.read_file(db.path), view.input_id()))
                    view.deleted_ok()
                except:
                    view.id_not_found()
            case _:  # Error
                view.input_error()
