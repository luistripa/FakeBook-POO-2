from os import system

NUMBER_OF_TESTS = 16

system("cd src/ ; javac -Xlint -d ../bin/production/FakeBook Main.java ; cd ..")

COMMAND_RUN = "cd bin/production/FakeBook ; java Main < ../../../FakeBookTests/fakebook%.2din.txt > ../../../tests/fakebook%.2d_test_out.txt"

for i in range(0, NUMBER_OF_TESTS):
	system(COMMAND_RUN % (i, i))
	code = system("diff tests/fakebook%.2d_test_out.txt FakeBookTests/fakebook%.2dout.txt" % (i, i))
	if code != 0:
		print("------------------------")
		print("       FAILED %.2s        " % str(i))
		print("------------------------")
		exit(0)
	else:
		print("------------------------")
		print("       PASSED %.2s        " % str(i))
		print("------------------------")