import re
import string
import time
from datetime import datetime
import requests
import http.client
import json

def getLogFiles():

    f = open('log.config')
    files = []

    for l in f.readlines():
        files.append(l.strip())

    f.close()

    return files

def sendToServer(json):

    connection = http.client.HTTPConnection("localhost:8083")
    connection.request('POST', '/api/logs', json,
                       {'Content-type': 'application/json'})

    print(connection.getresponse().read().decode())

lastSentPoint = None

while True:

    for file in getLogFiles():

        f = open(file, 'r')

        for line in f.readlines():

            mach = re.search(r'\d{2}-\d{2}-\d{4} \d{2}:\d{2}:\d{2}', line)

            if mach == None or 'ERROR' not in line.upper():
                continue

            date = datetime.strptime(mach.group(), '%d-%m-%Y %H:%M:%S')

            if lastSentPoint != None and lastSentPoint < date:
                continue


            lastSentPoint = datetime.now()
            j = json.dumps({
                'date': mach.group(),
                'agent': 'Source 1',
                'alarm': line
            })

            print(line)
            sendToServer(j)

        f.close()

    time.sleep(5)