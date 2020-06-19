import time
from datetime import datetime
import random

def getRandom(start, end):
    return int(random.uniform(start, end))

def now():
    return datetime.now().strftime("%d-%m-%Y %H:%M:%S")

logsData = [
    'LOG SISTEM UPDATE [VERBOSE][{0}]: System updating ....',
    'LOGIN ERROR LOG[ERROR][{0}]: WRONG PASSWORD',
    'LOGIN INFO[INFO][{0}]: USER TEST LOGED IN',
    'DATABASE UPDATE INFO [INFO][{0}]: Data read from server',
    'MILITARY APP1 LOG[ERROR][{0}]: NULL POINT EXCEPTION',
]

def getLogFiles():

    f = open('log.config')
    files = []

    for l in f.readlines():
        files.append(l.strip())

    f.close()

    return files

logFiles = getLogFiles()
fileCount = len(logFiles)
logsCount = len(logsData)

while(True):

    indexFile = getRandom(0, fileCount)
    indexLog = getRandom(0, logsCount)

    file = logFiles[indexFile]
    log = logsData[indexLog].format(now())

    f = open(file, 'a+')
    f.write(log + '\n')
    f.close()

    print(log)

    time.sleep(4)

