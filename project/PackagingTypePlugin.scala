import sbt._

object PackagingTypePlugin extends AutoPlugin {
  override val buildSettings: Seq[Nothing] = {
    sys.props += "packaging.type" -> "jar"
    Nil
  }
}
