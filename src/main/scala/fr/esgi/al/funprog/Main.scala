package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import fr.esgi.al.funprog.argument.input.InputArgumentParser
//import fr.esgi.al.funprog.argument.input.InputArgumentParser
//import fr.esgi.al.funprog.argument.output.OutputArgumentParser

object Main extends App {

  // lire le fichier de conf
  val conf: Config = ConfigFactory.load()

  val input = InputArgumentParser.getInput(this.args)
  input.print()

  val output = InputArgumentParser.getOutput(this.args)
  output.print()
  // choisir le type d'input
//  val input = InputArgumentParser.parse(this.args, conf)

  // choisir le type d'output
//  val output = OutputArgumentParser.parse(this.args)

  // faire agir la tondeuse

  // enregistrer le résultat en fonction de la sortie choisie


}
