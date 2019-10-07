package hello.world

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.native.{Text, TouchableOpacity, View}

import scala.scalajs.js.Dynamic.literal

@react class Note extends StatelessComponent {

  case class Props(deleteMethod: () => Unit, date: String, note: String)

  val note = literal(
    position = "relative",
    padding = 20,
    paddingRight = 100,
    borderBottomWidth = 2,
    borderBotttomColor = "#ededed"
  )

  val noteText =
    literal(paddingLeft = 20, borderLeftWidth = 10, borderLeftColor = "#E91E63")

  val noteDelete = literal(
    position = "absolute",
    justifyContent = "center",
    aligntItems = "center",
    backgroundColor = "#2980b9",
    padding = 10,
    top = 10,
    bottom = 10,
    right = 10
  )

  val noteDeleteText = literal(color = "white")

  override def render(): ReactElement =
    View(style = note)(
      Text(style = noteText)(props.date),
      Text(style = noteText)(props.note),
      TouchableOpacity(
        TouchableOpacity
          .Props(onPress = props.deleteMethod, style = noteDelete)
      )(Text(style = noteDeleteText)("D"))
    )
}
