(ns form-gen.frontend.app
  (:require
   [form-gen.frontend.form :as f]
   [form-gen.frontend.schema :as schema]
   [reagent.dom :as reagent-dom]))

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (let [root-el (.getElementById js/document "app")]
    (reagent-dom/unmount-component-at-node root-el)
    (reagent-dom/render [f/fork-form schema/ExampleSixColFormSchema] root-el)))


(defn ^:export init
  "Run application startup logic."
  []
  (render))
