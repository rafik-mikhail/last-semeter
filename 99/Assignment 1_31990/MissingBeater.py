import numpy as np
from matplotlib import pyplot as plt
from scipy import signal
from scipy.signal import butter,filtfilt, lfilter
from PIL import Image


def Implement_Notch_Filter(fs, band, freq, ripple, order, filter_type, data):
    from scipy.signal import iirfilter, lfilter
    nyq  = fs/2.0
    low  = freq - band/2.0
    high = freq + band/2.0
    low  = low/nyq
    high = high/nyq
    b, a = iirfilter(order, [low, high], rp=ripple, btype='bandstop',
                     analog=False, ftype=filter_type)
    filtered_data = lfilter(b, a, data)
    return filtered_data

def butter_lowpass(cutoff, fs, order=5):
    nyq = 0.5 * fs
    normal_cutoff = cutoff / nyq
    b, a = butter(order, normal_cutoff, btype='low', analog=False)
    return b, a

def butter_lowpass_filter(data, cutoff, fs, order=5):
    b, a = butter_lowpass(cutoff, fs, order=order)
    y = lfilter(b, a, data)
    return y


def MissingBeater(content, windowSize):
    # plt.figure(1)
    # plt.plot(content)
    # plt.savefig(drawer + 'original.png')

    fs = 256.0 
    f0 = 60.0 
    Q = 30.0  

    x = Implement_Notch_Filter(fs,1,50,0,2,"butter",content)

    y = butter_lowpass_filter(content, 45, fs, 6)

    z = butter_lowpass_filter(x, 45, fs, 6)

    # plt.figure(2)
    # plt.plot(x)
    # plt.savefig(drawer + 'notch.png')

    # plt.figure(3)
    # plt.plot(y)
    # plt.savefig(drawer + 'band.png')
    # plt.figure(4)
    # plt.plot(z)
    # plt.savefig(drawer + 'notch_then_band.png')


    # plt.figure(1)
    # plt.subplot(211)
    # plt.plot(content)
    # plt.subplot(212)
    # plt.plot(z)
    # plt.savefig("Before_After_FilerFull.jpg")

























    plt.figure(1)
    plt.subplot(211)
    plt.plot(content[:2000])
    plt.subplot(212)
    plt.plot(z[:2000])
    plt.savefig("output/002Before_After_Filer.jpg")
























    # Image.open('Before_After_Filer.png').save('Before_After_Filter.jpg')


    diffed = z.copy()
    # diffed = content.copy()
    
    for i in range(3,len(y)-2):
        diffed[i] = 1/8 * (-(z[i-2]) -(2*z[i-1]) + (2*z[i+1]) + (z[i+2]))

    # plt.figure(5)
    # plt.plot(diffed)
    # plt.savefig(drawer + 'differentiated.png')
    ###################################################################################################################################
    # diffed2 = diffed.copy()

    # for i in range(3,len(y)-2):
    #     diffed2[i] = 1/8 * (-(diffed2[i-2]) -(2*diffed2[i-1]) + (2*diffed2[i+1]) + (diffed2[i+2]))

    # plt.figure(90000000009)
    # plt.plot(diffed2)
    # plt.savefig(drawer + 'differentiated2.png')

    
    # p = np.sort(diffed2)
    # ww = ((int)(-0.01 * len(p)))
    # thresholddddddd = p[ww] * 0.8
    # print(len(p))
    # print(ww)
    # print(p[ww])
    # x = np.sort([123,5,8])
    # print(x[0])
    # print(x[-1])

    # print("woaaaaaah        \t\t", peakValue/peakCounter)
    # print(peakCounter)

    # positions = []
    # for pos in range(len(diffed2)):
    #     if(diffed2[pos]>thresholddddddd):
    #         positions.append(pos)
    
    ###########################################################################################################################
    squared = np.power(diffed, 2)

    # plt.figure(6)
    # plt.plot(squared)
    # plt.savefig(drawer + 'squared.png')

    smoothed = squared.copy()
    for i in range(windowSize-1, len(smoothed)):
        sum = 0
        for j in range(1, windowSize):
            sum += squared[i-j]
        sum += squared[i]
        smoothed[i] = sum/windowSize
    
    # plt.figure(7)
    # plt.plot(smoothed)
    # plt.savefig(drawer + 'smoothed.png')


    npArray = np.sort(smoothed)
    ointe = ((int)(-0.01 * len(npArray)))
    thresholddddddd = npArray[ointe] * 0.97
    # print(thresholddddddd,"theeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrr")
    # print()
    # print(npArray[ointe])
    # print()


    
    hey = [0]*len(smoothed)
    over = False
    values = []
    stamps = []
    thresh = [0.012,0.007,0.0055]
    for i in range(len(smoothed)):
        # thresh[iteration]
        if smoothed[i] > thresholddddddd:
            over = True
            values.append(smoothed[i])
            stamps.append(i)
        elif over:
            max = values[0]
            pos = stamps[0]
            for q in range(len(values)):
                if values[q] > max:
                    max = values[q]
                    pos = stamps[q]
            hey[pos] = max
            over = False
            values = []
            stamps = []


    # plt.figure(8)
    # plt.plot(hey)
    # plt.savefig(drawer + 'detected.png')

    done = hey.copy()
    for q in range((windowSize//2),(len(hey) - windowSize//2)):
        if(hey[q] > 0):
            pos0 = q - windowSize//2
            max = hey[pos0]
            maxPos = pos0
            for w in range(windowSize//2):
                
                if (max > hey[pos0 + w]):
                    done[pos0 + w] = 0
                else:
                    done[maxPos] = 0
                    maxPos = pos0 + w
                    max = hey[pos0 + w]

            if max > hey[q]:
                done[q] = 0;
            else:
                done[maxPos] = 0
                maxPos = q
                max = hey[q]

            for w in range(windowSize//2):
                if (max > hey[q + 1 + w]):
                    done[q + 1 + w] = 0
                else:
                    done[maxPos] = 0
                    maxPos = q + 1 + w
                    max = hey[q + 1 + w]

    # plt.figure(9)
    # plt.plot(done)
    # plt.savefig(drawer + 'detected2.png')

    timeStamps = []
    tk = 0
    for pla in range(len(done)):
        if done[pla] > 0:
            if pla<2000:
                tk += 1
            timeStamps.append(pla)
    
    # print(len(timeStamps))

    plt.figure(2)
    npdone = np.array(done)
    nptimestamps = np.array(timeStamps)
    smallTime = []
    for t in timeStamps:
        if t< 2000:
            smallTime.append(t)
    npSmall = np.array(smallTime)
    plt.plot(npSmall,npdone[npSmall],"*r")
    plt.plot(done[:2000])
    plt.savefig("output/002filtered_15.jpg")






# unfiltered_25.jpg
    # plt.figure(3)
    # noOrig = np.array(z)
    # nptimestamps = np.array(timeStamps)
    # smallTime = []
    # for t in timeStamps:
    #     if t< 2000:
    #         smallTime.append(t)
    # npSmall = np.array(smallTime)
    # plt.plot(npSmall,noOrig[npSmall],"*r")
    # plt.plot(z[:2000])
    # plt.savefig("output/DetectedR_15.jpg")

    timeIntervals = [0] * (len(timeStamps)-1)
    # print(len(timeIntervals))
    # print(len(timeStamps)-1)
    wer3 = 0 
    for bva in range(len(timeStamps)-1):
        if(timeStamps[bva] <2000):
            wer3 += 1
        timeIntervals[bva] = timeStamps[bva+1] - timeStamps[bva]

    plt.figure(10)
    z1=  0
    plt.plot(np.arange(wer3),timeIntervals[:wer3])
    plt.savefig("output/first2000beats.jpg")

    plt.figure(100)
    z1=  0
    plt.plot(np.arange(len(timeIntervals)),timeIntervals)
    plt.savefig("output/all000beats.jpg")
    # .append([1/256 * x for x in timeIntervals])
    
        # print(new_intervals)
    # print(q12)
    # print(new_intervals)
    # print(timeStamps)














    plt.figure(3)
    new_intervals = [0.0]
    for x in timeIntervals:
        new_intervals.append(x*1/256*1/1000)
    q12 = np.arange(1,len(timeStamps)+1)
    plt.plot(q12, new_intervals)
    plt.savefig('output/0002RR.jpg')









    # print(timeIntervals[5])

    # print(tk)
    # print(len(timeStamps))
    

    # plt.figure(10)
    # plt.plot(timeIntervals)
    # plt.savefig(drawer + 'intervals.png')


    # plt.figure(11)
    # plt.plot(timeStamps)
    # plt.savefig(drawer + 'stamps.png')
    # sw = []
    # for something in range(len(hey)):
    #     if(hey[something] == 1):
    #         # print(something)
    #         sw.append(something)
    # for something in range(1,len(sw)):
    #     if(sw[something]-sw[something-1]) < 200:
    #         print(sw[something]-sw[something-1], something)

    # print("len(sw)", len(sw))

    # sw = []
    # for something in range(len(done)):
    #     if(done[something] == 1):
    #         # print(something)
    #         sw.append(something)
    # for something in range(1,len(sw)):
    #     if(sw[something]-sw[something-1]) < 200:
    #         print(sw[something]-sw[something-1], something)

    # print("len(sw)", len(sw))



    # quacker = 0
    # quaaa = 0
    # for quack in done:
        
    #     if quack > 0 and quaaa < 2000:
    #         # print("quack", quack)
    #         quacker += 1
    #         print("quacker", quacker)
    #     quaaa += 1

    plt.show()
    return 70


with open("Data2.txt", "r") as f:
    cont = f.readlines()
cont = [float(x.strip()) for x in cont]
# x = MissingBeater(cont, 5)
# drawing = 0
# drawer = ''
# iteration = 0
# for x in [5, 15, 25]:
#     drawing += 1
#     drawer =(str) (drawing)
#     w = MissingBeater(cont, x)
#     iteration += 1

x=MissingBeater(cont, 15)