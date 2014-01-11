(ns examples.integrator
  (:use com.konato.ode))

;; Ordinary Différential Equations Solvers - Integrator 1/s example using transfer2ode conversion function
;;
;; Methods based on: Gerald-Wheatley, 2003, Applied Numerical Analysis, Seventh Edition,
;; Pearson Education, ISBN 0-321-13304-8.
;;
;;
;; Copyright (c) 2009 Stephane Rousseau (stephaner gmail.com). All rights reserved.
;;
;; The use and distribution terms for this software are covered by the Eclipse Public License 1.0,
;; which can be found in the file epl-v10.html at the root of this distribution.
;; By using this software in any fashion, you are agreeing to be bound by the terms of this license.
;; You must not remove this notice, or any other, from this software.

(def NUM [1])
(def DEN [1 0])
(def inp [1])
(def fps (transfer2ode NUM DEN inp))
(odesolve rk4 (:dotfps fps) {:x1 0.0} :t 0 2 0.01 (:outfps fps))
(= 2.0 (keepdecim (last (:y1 (odesolve rk4 (:dotfps fps) {:x1 0.0} :t 0 2 0.01 (:outfps fps)))) 5));should be true
