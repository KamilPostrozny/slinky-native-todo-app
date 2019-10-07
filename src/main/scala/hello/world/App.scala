package hello.world

import java.util.UUID

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.native._

import scala.scalajs.js.Date
import scala.scalajs.js.Dynamic.literal

@react class App extends Component {

  import App._

  type Props = Unit
  case class State(noteList: List[NoteClass], noteText: String)

  def initialState = State(Nil, "")

  def deleteNote(id: String): Unit =
    setState(_.copy(noteList = state.noteList.filterNot(_.id == id)))

  def addNote: Unit =
    state.noteText match {
      case "" =>
      case other =>
        setState(
          _.copy(
            noteList = state.noteList :+ NoteClass(
              id = UUID.randomUUID().toString,
              date = Date(),
              note = other
            ),
            noteText = ""
          )
        )
    }

  def render(): ReactElement = {
    View(style = container)(
      View(style = headerStyle)(Text(style = headerTextStyle)("- NOTER -")),
      ScrollView(ScrollView.Props(contentContainerStyle = scrollContainer))(
        state.noteList.map(
          element =>
            Note(
              deleteMethod = () => deleteNote(element.id),
              date = element.date,
              note = element.note
            ).withKey(element.id)
        )
      ),
      View(style = footer)(
        TextInput(
          onChangeText = { text =>
            setState(_.copy(noteText = text))
          }: String => Unit,
          value = state.noteText,
          style = textInput,
          placeholder = ">note",
          placeholderTextColor = "white",
          underlineColorAndroid = "transparent",
        )
      ),
      TouchableOpacity(
        TouchableOpacity.Props(onPress = () => addNote, addButton)
      )(Text(style = buttonText)("+"))
    )
  }
}

object App {

  val container = literal(flex = 1)

  val textInput = literal(
    alignSelf = "stretch",
    color = "#fff",
    padding = 20,
    backgroundColor = "#252525",
    borderTopWidth = 2,
    borderTopColor = "#ededed"
  )

  val headerStyle = literal(
    backgroundColor = "#E91E63",
    alignItems = "center",
    justifyContent = "center",
    boderBottomWidth = 10,
    borderBottomColor = "#ddd"
  )

  val headerTextStyle =
    literal(color = "white", fontSize = 18, padding = 26)

  val scrollContainer =
    literal(flex = 1, marginBottom = 100)

  val footer =
    literal(position = "absolute", bottom = 0, left = 0, right = 0, zIndex = 10)

  val addButton = literal(
    position = "absolute",
    zIndex = 11,
    right = 20,
    bottom = 90,
    backgroundColor = "#E91E63",
    width = 90,
    height = 90,
    borderRadius = 50,
    alignItems = "center",
    justifyContent = "center",
    elevation = 8
  )

  val buttonText =
    literal(color = "#fff", fontSize = 24)
}
