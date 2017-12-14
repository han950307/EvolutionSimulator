from matplotlib import pyplot as plt
import numpy as np
import re
import os

class Visualizer(object):
    def get_filepaths(self, directory):
        """
        retrieves a list of all filepaths from this directory
        """
        output = []

        onlyfiles = [
            f
            for f in os.listdir(os.path.realpath(directory))
            if os.path.isfile(os.path.join(os.path.realpath(directory), f))
        ]

        for k in onlyfiles:
            output.append(os.path.realpath(os.path.join(directory, k)))

        return output

    def make_graphs(self, directory):
        paths = self.get_filepaths(directory)
        my_data = []
        for p in paths:
            with open(p) as f:
                num = re.search(r"_(\d+)\.txt", p).group(1)
                li = []
                for l in f:
                    li.append(float(l))
                my_data.append((int(num), len(li), li))

        my_data.sort()

        for num, size, li in my_data:
            plt.clf()
            plt.hist(li, 100, range=(30, 130), density=True)
            plt.ylim(0, .45)
            plt.savefig("figs/dist_{}.png".format(num))

        fig, ax = plt.subplots(nrows=1, ncols=1)
        sizes = [k[1] for k in my_data]
        nums = [k[0] for k in my_data]
        ax.plot(nums, sizes)

        fig.savefig("figs/size.png")
