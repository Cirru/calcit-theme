
(ns calcit-theme.comp.expr
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> action-> mutation-> list-> <> div button textarea span]]
            [respo.comp.space :refer [=<]]
            [calcit-theme.config :refer [dev?]]
            [calcit-theme.theme :as theme]))

(defcomp
 comp-expr
 (expr tailing? root?)
 (list->
  {:style (merge theme/style-expr (theme/decorate-expr expr tailing? root?))}
  (->> expr
       (map-indexed
        (fn [idx child]
          [idx
           (if (string? child)
             (div
              {:style (merge theme/style-leaf (theme/decorate-leaf child (zero? idx)))}
              (<> child))
             (comp-expr child (= (inc idx) (count expr)) false))])))))

(defn render-expr [data] (comp-expr data false true))
