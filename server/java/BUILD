java_binary(
    name = "server",
    srcs = glob(["**/*.java"]),
    main_class = "io.grpc.demo.exchange_o_gram.ExchangeOGramServer",
    visibility = ["//visibility:public"],
    runtime_deps = [
        "@org_pubref_rules_protobuf//java:netty_runtime_deps",
    ],
    deps = [
        "//proto:exchange_o_gram",
        "@org_pubref_rules_protobuf//java:grpc_compiletime_deps",
    ],
)
