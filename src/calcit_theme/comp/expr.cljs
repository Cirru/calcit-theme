
(ns calcit-theme.comp.expr
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> action-> mutation-> list-> <> div button textarea span]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [respo-md.comp.md :refer [comp-md]]
            [calcit-theme.config :refer [dev?]]))

(defcomp
 comp-expr
 (expr)
 (list->
  {:style {:display :inline-block, :border-left "1px solid #ccc", :color :white}}
  (->> expr
       (map-indexed
        (fn [idx child] [idx (if (string? child) (div {} (<> child)) (comp-expr child))])))))
