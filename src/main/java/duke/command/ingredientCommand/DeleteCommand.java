package duke.command.ingredientCommand;

import duke.command.Cmd;
import duke.exception.DukeException;
import duke.list.GenericList;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a specific {@link Cmd} used to delete a {@link Task} from the {@link TaskList}.
 */
public class DeleteCommand<T> extends Cmd<T> {
    private int taskNb;

    public DeleteCommand(int taskNb) {
        this.taskNb = taskNb;
    }

    @Override
    public void execute(GenericList<T> taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNb <= taskList.size() && taskNb > 0) {
            T removed = taskList.removeEntry(taskNb - 1);

            try {
                storage.removeFromFile(taskNb - 1);
            } catch (IOException e) {
                throw new DukeException("Error while deleting the task from the hard disc");
            }
            ui.showRemovedTask(removed.toString(), taskList.size());
        } else {
            throw new DukeException("Enter a valid task number after delete, between 1 and " + taskList.size());
        }
    }
}
