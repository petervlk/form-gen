(ns user)

(defmacro jit [sym]
  `(requiring-resolve '~sym))

(defn start-shadow-cljs
  ([]
   (start-shadow-cljs :app))
  ([build-id]
   (when (nil? @@(jit shadow.cljs.devtools.server.runtime/instance-ref))
     ((jit shadow.cljs.devtools.server/start!))
     ((jit shadow.cljs.devtools.api/watch) build-id)
     (loop []
       (when (nil? @@(jit shadow.cljs.devtools.server.runtime/instance-ref))
         (Thread/sleep 250)
         (recur))))))

(defn cljs-repl
  "Connects to a given build-id. Defaults to `:app`."
  ([]
   (cljs-repl :app))
  ([build-id]
   (start-shadow-cljs build-id)
   ((jit shadow.cljs.devtools.api/nrepl-select) build-id)))

(defn browse []
  ((jit clojure.java.browse/browse-url) "http://localhost:9090"))

(comment
  (browse)
  (cljs-repl))
