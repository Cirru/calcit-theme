
(ns calcit-theme.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> action-> mutation-> <> div button textarea span]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [respo-md.comp.md :refer [comp-md]]
            [calcit-theme.config :refer [dev?]]
            [cljs.reader :refer [read-string]]
            [calcit-theme.comp.expr :refer [comp-expr render-expr]]))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel)
       states (:states store)
       data (read-string
             "[\"defn\" \"decorate-expr\" [\"expr\" \"tailing?\" \"root?\"] [\"cond\" [\"root?\" [\"{}\" [\":display\" \":inline-block\"]]] [\"tailing?\" [\"{}\" [\":display\" \":inline-block\"]]] [[\"expr-simple?\" \"expr\"] [\"{}\" [\":display\" \":inline-block\"] [\":border-left\" \"\\\"none\"] [\":border-bottom\" \"\\\"1px solid white\"]]] [\":else\" [\"{}\"]]]]")]
   (div
    {:style (merge ui/global ui/fullscreen {:background-color :black})}
    (render-expr data)
    (when dev? (cursor-> :reel comp-reel states reel {})))))
