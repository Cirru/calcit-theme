
(ns calcit-theme.comp.expr
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> action-> mutation-> list-> <> div button textarea span]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [respo-md.comp.md :refer [comp-md]]
            [calcit-theme.config :refer [dev?]]
            [calcit-theme.theme :as theme]))

(defcomp
 comp-expr
 (expr tailing?)
 (list->
  {:style (merge theme/style-expr (theme/decorate-expr expr tailing?))}
  (->> expr
       (map-indexed
        (fn [idx child]
          [idx
           (if (string? child)
             (div
              {:style (merge theme/style-leaf (theme/decorate-leaf child (zero? idx)))}
              (<> child))
             (comp-expr child (= (inc idx) (count expr))))])))))
