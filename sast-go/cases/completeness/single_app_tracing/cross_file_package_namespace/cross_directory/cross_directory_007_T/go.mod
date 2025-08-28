module cross_directory_007_T

go 1.14

replace (
	cross_directory_007_T/cross_a => ./cross/cross_a
	cross_directory_007_T/cross_a_shadow => ./cross/cross_a_shadow
)

require (
	cross_directory_007_T/cross_a v0.0.0-00010101000000-000000000000 // indirect
	cross_directory_007_T/cross_a_shadow v0.0.0-00010101000000-000000000000 // indirect
)
