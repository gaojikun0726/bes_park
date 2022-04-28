import socket,os,struct,string
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(('172.16.12.103',1204))
s.settimeout(10)
boolean = True


def crc16(x, invert):
    a = 0xFFFF
    b = 0xA001
    for byte in x:
        a ^= ord(byte)
        for i in range(8):
            last = a % 2
            a >>= 1
            if last == 1:
                a ^= b
    s = '{:04X}'.format(a)    
    return s[2:4]+s[0:2] if invert == True else s[0:2]+s[2:4]
while boolean:
	filepath = "E:\program.txt"
	if os.path.isfile(filepath):
		fileinfo_size=struct.calcsize('128sl') 
		print 'client filepath: ',filepath
		fo = open(filepath,'rb')
		fsize = os.path.getsize(filepath)
		print fsize
		filedata = fo.read(fsize)
		crc12 = crc16(filedata,True)
		print crc12
		s.send(filedata + crc12)
			
		print s.recv(1024);
		fo.close()
		print 'send over...'
		boolean = False
s.close()
