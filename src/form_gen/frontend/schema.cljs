(ns form-gen.frontend.schema
  (:require [malli.util :as mu]))

(def Email
  [:re
   {:error/message "invalid email form"}
   #"^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$"])

(def NonEmptyString [:string {:min 1}])

(def ExampleSixColFormSchema
  [:map
   [:first-name
    (-> NonEmptyString
        (mu/update-properties assoc :ui/render true)
        (mu/update-properties assoc :ui/col-span 3))]
   [:last-name
    (-> NonEmptyString
        (mu/update-properties assoc :ui/render true)
        (mu/update-properties assoc :ui/col-span 3)
        (mu/update-properties assoc :ui/continue-row? true))]
   [:email
    (-> Email
        (mu/update-properties assoc :ui/render true)
        (mu/update-properties assoc :ui/label "Email Address")
        (mu/update-properties assoc :ui/input-type "email")
        (mu/update-properties assoc :ui/col-span 4))]
   [:street-address
    (-> NonEmptyString
        (mu/update-properties assoc :ui/render true))]
   [:city
    (-> NonEmptyString
        (mu/update-properties assoc :ui/render true)
        (mu/update-properties assoc :ui/col-span 2))]
   [:region
    (-> NonEmptyString
        (mu/update-properties assoc :ui/render true)
        (mu/update-properties assoc :ui/label "State / Province")
        (mu/update-properties assoc :ui/col-span 2)
        (mu/update-properties assoc :ui/continue-row? true))]
   [:postal-code
    (-> NonEmptyString
        (mu/update-properties assoc :ui/render true)
        (mu/update-properties assoc :ui/label "ZIP / Postal code")
        (mu/update-properties assoc :ui/col-span 2)
        (mu/update-properties assoc :ui/continue-row? true))]])
