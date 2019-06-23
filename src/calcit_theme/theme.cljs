
(ns calcit-theme.theme
  (:require [respo-ui.core :as ui] [clojure.string :as string] [hsl.core :refer [hsl]])
  (:require-macros [clojure.core.strint :refer [<<]]))

(defn expr-simple? [expr] (and (every? string? expr) (< (count expr) 6)))

(defn decorate-expr [expr tailing? root?]
  (cond
    root? {:display :inline-block, :margin-bottom 0}
    tailing? {:display :inline-block, :margin-bottom 0}
    (expr-simple? expr)
      {:display :inline-block,
       :border-left "none",
       :border-bottom (<< "1px solid ~(hsl 0 0 100 0.3)"),
       :padding "2px 4px",
       :margin-bottom 0}
    :else {}))

(defn decorate-leaf [text leading?]
  (cond
    (string/starts-with? text ":") {:color (hsl 240 30 64)}
    (or (string/starts-with? text "\"") (string/starts-with? text "|"))
      (if (string/includes? text " ")
        {:color (hsl 120 60 56), :background-color (hsl 0 0 100 0.12)}
        {:color (hsl 120 60 56)})
    (string/starts-with? text "#\"") {:color (hsl 300 60 56)}
    (or (= text "true") (= text "false")) {:color (hsl 250 50 60)}
    (= text "nil") (:color (hsl 310 60 40))
    (re-matches (re-pattern "^-?\\d") text) {:color (hsl 0 70 40)}
    leading? {:color (hsl 40 85 60)}
    :else {}))

(def style-expr
  {:display :block,
   :border-left (<< "1px solid ~(hsl 0 0 100 0.3)"),
   :color :white,
   :vertical-align :top,
   :padding "4px 4px 0px 8px",
   :margin-left 8,
   :margin-bottom 4})

(def style-leaf
  {:display :inline-block,
   :text-align :top,
   :font-family ui/font-code,
   :margin "0 4px",
   :padding "0 4px",
   :color (hsl 200 14 60)})
