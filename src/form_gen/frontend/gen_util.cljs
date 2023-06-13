(ns form-gen.frontend.gen-util
  (:require
   [malli.core :as m]
   [malli.util :as mu]
   [clojure.string :as string]))

(defn- capitalized-label
  [label-val]
  (as-> label-val label
   (name label)
   (string/split label #"[ -]")
   (map string/capitalize label)
   (string/join " " label)))

(defn- filter-namespaced
  [m ns-prefix]
  (into {} (filter (fn [kv] (= (name ns-prefix) (namespace (key kv)))) m)))

(defn schema->form-inputs
  [schema]
  (->> schema
       mu/subschemas
       (filter (comp :ui/render m/properties :schema))
       (map (juxt (comp last :path) (comp m/properties m/form :schema)))
       (map (fn [[id schema-props]] (-> schema-props
                                        (filter-namespaced :ui)
                                        (assoc :ui/id id)
                                        (update :ui/label #(or % (capitalized-label id))))))))
