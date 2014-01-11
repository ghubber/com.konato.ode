# com.konato.ode = An Ordinary Differentials Equations Solvers library for Clojure

com.konato.ode is an ordinary differentials equations solvers and simulation library in Clojure.
It does implements several fixed steps solvers: Euler, an Euler modified version, Runge-Kutta order 4 and a Runge-Kutta using parameters to implement RK4, Runge-Kutta-Fehlberg. It does implement a variable step ODE solver based on the popular rfk45 method. Others RK fixed or variable methods can be easily implemented using the RKP method. Functions are provided to convert State Space and Transfer functions to ODE who can be used by the system.

Methods based on: Gerald-Wheatley, 2003, Applied Numerical Analysis, Seventh Edition,
Pearson Education, ISBN 0-321-13304-8.

Copyright (c) 2009 Stephane Rousseau (stephaner gmail). All rights reserved.

[com.konato.ode release info](http://www.konato.com/2009/07/08/com-konato-ode)

The use and distribution terms for this software are covered by the Eclipse Public License 1.0,
which can be found in the file epl-v10.html at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by the terms of this license.
You must not remove this notice, or any other, from this software.

# How To Use It?

Create a JAR by running `ant` and add the location of the jar file to
your CLASSPATH.

# Functions


Top-Level functions for user
----------------------------
odesolve "Solve an ODE system using either an fixed step or a variable step solver"

Top-Level functions to add ODE methods
--------------------------------------
make-rkp "Make a new Runge-Kutta method using parameters."

Data Types
----------
An ODE system is defined using an hashmap, examples:

       {:x #(:xdot %), :xdot #(/ (+ (* -1.0 k (:x %)) (* -1.0 fv (:xdot %))) m)} ;a classical damped oscillator
       {:y #(+ (* (:x %) -2.0) (* (:y %) -1.0))} ;dy/dx=-2x-y used in unit testing

Major functions
---------------
* `euler`	"Evaluate an ODE system using Euler method."
* `eulerpc`	"Evaluate an ODE system using an improved Euler method using predictor and corrector."
* `rk4`		"Evaluate an ODE system using an Runge-Kutta order 4."
* `rkp`		"Evaluate an ODE system using paramatrized Runge-Kutta method, this allows to implements various orders and methods."
* `rkp4`	"Evaluate an ODE system using a parametrized Runge-Kutta order 4."
* `rkf45`	"Evaluate an ODE system usgin a parametrized Runge-Kutta-Feltberg order 45 with error estimation"
* `state2ode`	"Convert a states equations to an ODE system."
* `transfer2ode`	 Convert a transfert function to an ODE system.

Auxiliary Functions
-------------------
* `interval-seq` "Produce a sequence of number beginning at v0 incremented by stepsz."
* `max-vec`	     "Returns the max x of a vector."
* `keepdecim`    "Keep a number of decimals."


# Examples

Tutorials are available at my blog [stephaner Blog](http://www.konato.com/blog/).

The 'src/examples' directory contains some examples to get you started.

First example is a classical Damped Harmonic Oscillator.
It shows usage of three methods to describe and simulate a system : ODE system, State variables representation and transfer function.
Based on example 1.14 of "Interactive Dynamic System Simulation (G.A. Korn, 1989)

Second example is the classic pen pendulum.
Based on [An Approach to Solving Ordinary Differential Equations (I.Urieli, Winter 2002)](http://www.ent.ohiou.edu/~urieli/odes/odes.html).

Third example is a three equations system used in other implementation.
Based on [Example 1" of the Matlab product documentation](http://www.mathworks.com/access/helpdesk/help/techdoc/index.html?/access/helpdesk/help/techdoc/ref/ode45.html).

Forth example is a digital PID controller.
Based on example 7.9 of "Interactive Dynamic System Simulation (G.A. Korn,1989)

Fifth example is a Electric bicycle simulation.
Based on [An Approach to Solving Ordinary Differential Equations (I.Urieli, Winter 2002)](http://www.ent.ohiou.edu/~urieli/odes/odes.html).

Sixth example is a study showing the accumulated error with various solvers.

The 'src/com/konato/ode/tests' directory contains tests used by example in the reference book.

# Exporting data to gnuplot ##

You may use the following savedata function to export data to an external file. It need use of a clojure.contrib.duck-streams library.

    (use 'clojure.contrib.duck-streams)
    (defn savedata
    "Copyright (c) 2009 Stephane Rousseau (stephaner konato.com). All rights reserved.

    The use and distribution terms for this software are covered by the Eclipse Public License 1.0,
    which can be found in the file epl-v10.html at the root of this distribution.
    By using this software in any fashion, you are agreeing to be bound by the terms of this license.
    You must not remove this notice, or any other, from this software."
    [filename & more]
    (loop [
       sqs more
       onetime true
      ]
      (if (empty? (first sqs))
          nil
	      (let [toprn (map #(first %) sqs)]
	          (if onetime
		      (with-out-writer filename (doall (map #(print %) (interpose " " toprn))) (println));delete first
		      (with-out-append-writer filename (doall (map #(print %) (interpose " " toprn))) (println)))
		      (recur (map #(rest %)  sqs) false))))
		      )

Usage example to save :x and :t values into a filename datafile1.dat `(savedata "datafile1.dat" (:x res) (:t res))`.
# COMMON ISSUES ##

none

# TODO ##

* More tutorials
* More examples
* Additional libraries

# Tools reference ##

* [clojure](http://clojure.org)
* [test-is](http://processing.org/)
