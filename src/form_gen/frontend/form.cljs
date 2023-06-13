(ns form-gen.frontend.form
  (:require
   [form-gen.frontend.gen-util :as gen]
   [fork.re-frame :as fork]
   [re-frame.core :as rf]
   [malli.core :as m]
   [malli.error :as me]))

(rf/reg-event-fx
 :submit-handler
 (fn [{db :db} [_ {:keys [values _dirty path]}]]
   {:db             (fork/set-submitting db path true)
    :dispatch-later [{:ms       1000
                      :dispatch [:resolved-form path values]}]}))

(rf/reg-event-fx
 :resolved-form
 (fn [{db :db} [_ path values]]
   (js/alert values)
   {:db (fork/set-submitting db path false)}))

(defn form-input-attrs
  [input-base-attrs error?]
  (let [classes-base      ["block"
                           "w-full"
                           "rounded-md"
                           "border-0"
                           "py-1.5"
                           "shadow-sm"
                           "ring-1"
                           "ring-inset"
                           "placeholder:text-gray-400"
                           "focus:ring-2"
                           "focus:ring-inset"
                           "sm:text-sm"
                           "sm:leading-6"]
        classes-error     ["focus:ring-red-500" "ring-red-300" "text-red-900"]
        classes-ok        ["focus:ring-indigo-600" "ring-gray-300" "text-gray-900"]
        input-error-attrs {:aria-invalid     "true"
                           :aria-describedby "email-error"}]
    (if error?
      (-> input-base-attrs
          (assoc :class (into classes-base classes-error))
          (merge input-error-attrs))
      (assoc input-base-attrs :class (into classes-base classes-ok)))))

(defn form-input
  [{:keys [values
           touched
           errors
           handle-change
           handle-blur]}
   {:ui/keys [label continue-row? col-span type id]
    :or      {type "text"}}]
  (let [id-error       (str id "-error")
        error?         (and (touched id) (first (get errors id)))
        col-span-class (if (and (nat-int? col-span) (< 0 col-span 6))
                         (str "sm:col-span-" col-span)
                         "col-span-full")
        coll-attrs     (if continue-row?
                         [col-span-class]
                         [col-span-class "sm:col-start-1"])
        input-attrs    (form-input-attrs
                         {:type      type
                          :name      id
                          :id        id
                          :value     (values id)
                          :on-change handle-change
                          :on-blur   handle-blur}
                         error?)]
    [:div
     {:class coll-attrs}
     [:label
      {:for   id
       :class ["block" "text-sm" "font-medium" "leading-6" "text-gray-900"]}
      label]
     [:div
      {:class ["mt-2"]}
      [:input input-attrs]
      (when error?
        [:p
         {:class ["mt-2" "text-sm" "text-red-600"]
          :id    id-error}
         error?])]]))

(defn form-6-cols
  [form-schema
   {:keys [form-id
           submitting?
           handle-submit]
    :as   fork-data}]
  [:form
   {:id        form-id
    :on-submit handle-submit}
   [:div
    {:class ["space-y-12"]}
    [:div
     {:class ["border-b" "border-gray-900/10" "pb-12"]}
     [:h2
      {:class ["text-base" "font-semibold" "leading-7" "text-gray-900"]}
      "Personal Information"]
     [:p
      {:class ["mt-1" "text-sm" "leading-6" "text-gray-600"]}
      "Use a permanent address where you can receive mail."]
     [:div
      {:class ["mt-10" "grid" "grid-cols-1" "gap-x-6" "gap-y-8" "sm:grid-cols-6"]}
      (for [ui-data (gen/schema->form-inputs form-schema)]
        ^{:key (:ui/id ui-data)}
        [form-input fork-data ui-data])]]]
   [:div
    {:class ["mt-6" "flex" "items-center" "justify-end" "gap-x-6"]}
    [:button
     {:type  "button",
      :class ["text-sm" "font-semibold" "leading-6" "text-gray-900"]}
     "Cancel"]
    [:button
     {:type     "submit"
      :disabled submitting?
      :class
      ["rounded-md" "bg-indigo-600" "px-3" "py-2" "text-sm" "font-semibold" "text-white"
       "shadow-sm" "hover:bg-indigo-500" "focus-visible:outline" "focus-visible:outline-2"
       "focus-visible:outline-offset-2" "focus-visible:outline-indigo-600"]}
     "Save"]]])

(defn fork-form
  [schema]
  [fork/form {:path              [:form-example]
              :form-id           "id"
              :validation        #(me/humanize (m/explain schema %))
              :prevent-default?  true
              :clean-on-unmount? true
              :keywordize-keys   true
              :on-submit         #(rf/dispatch [:submit-handler %])}
   (partial form-6-cols schema)])
