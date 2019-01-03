
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
            [calcit-theme.comp.expr :refer [comp-expr]]))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel)
       states (:states store)
       data (read-string
             "[\"defcomp\" \"comp-container\" [\"reel\"] [\"let\" [[\"store\" [\":store\" \"reel\"]] [\"states\" [\":states\" \"store\"]]] [\"div\" [\"{}\" [\":style\" [\"merge\" \"ui/global\" \"ui/row\"]]] [\"when\" \"dev?\" [\"cursor->\" \":reel\" \"comp-reel\" \"states\" \"reel\" [\"{}\"]]]]]]")]
   (div
    {:style (merge ui/global ui/fullscreen ui/row {:background-color :black})}
    (comp-expr data false)
    (when dev? (cursor-> :reel comp-reel states reel {})))))
