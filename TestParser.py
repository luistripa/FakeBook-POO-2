from os import system

TEST_LIST = [
]

NUMBER_OF_TESTS = 15

system("cd src/ ; javac -Xlint -d ../bin/production/FakeBook Main.java ; cd ..")

COMMAND_RUN = "cd bin ; java Main < ../WeKeepSecretsTests/%.2d_in_%s.txt > ../tests/%.2d_%s_test_out.txt"

for i in range(0, NUMBER_OF_TESTS):
	system(COMMAND_RUN % (i+1, TEST_LIST[i], i+1, TEST_LIST[i]))
	code = system("diff tests/%.2d_%s_test_out.txt WeKeepSecretsTests/%.2d_out_%s.txt" % (i+1, TEST_LIST[i], i+1, TEST_LIST[i]))
	if code != 0:
		print("------------------------")
		print("       FAILED %.2s        " % str(i+1))
		print("------------------------")
		exit(0)
	else:
		print("------------------------")
		print("       PASSED %.2s        " % str(i+1))
		print("------------------------")