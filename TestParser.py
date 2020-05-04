from os import system

TEST_LIST = [
	"base_add",
	"base_upload",
	"base_share",
	"minspace",
	"pre_add",
	"pre_upload",
	"pre_share",
	"pre_listfiles"
]

NUMBER_OF_TESTS = len(TEST_LIST)

system("cd src/ ; javac -Xlint -d ../out/production/FakeBook Main.java ; cd ..")

COMMAND_RUN = "cd out/production/FakeBook ; java Main < ../../../FakeBookTests/%d_in_%s.txt > ../../../tests/%d_%s_test_out.txt"

for i in range(0, NUMBER_OF_TESTS):
	system(COMMAND_RUN % (i+1, TEST_LIST[i], i+1, TEST_LIST[i]))
	code = system("diff tests/%d_%s_test_out.txt FakeBookTests/%d_out_%s.txt" % (i+1, TEST_LIST[i], i+1, TEST_LIST[i]))
	if code != 0:
		print("------------------------")
		print("       FAILED %.2s        " % str(i+1))
		print("------------------------")
		exit(0)
	else:
		print("------------------------")
		print("       PASSED %.2s        " % str(i+1))
		print("------------------------")